package com.xishanqu.redislock.core;

import com.google.common.base.Preconditions;
import com.google.common.hash.Funnel;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * 中文类名:
 * 中文描述:
 *
 * @Author BaoNing
 * @Time 2019/9/1
 */
@Configurable
public class BloomFilterHelper<T> {

    /**
     * hash函数数量
     */
    private int numHashFunctions;

    /**
     * bit数组长度
     */
    private int bitSize;

    private Funnel<T> funnel;


    /**
     * 布隆过滤器
     * @param funnel
     * @param expectedInsertions
     * @param fpp
     */
    public BloomFilterHelper(Funnel<T> funnel, int expectedInsertions, double fpp){
        Preconditions.checkArgument(funnel != null,"funnel不能为空");
        this.funnel = funnel;
        bitSize = optimalNumOfBits(expectedInsertions, fpp);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, bitSize);
    }


    /**
     * 根据key获取bitmap下标
     * @param value
     * @return
     */
    public int[] murmurHashOffset(T value){
        int[] offset = new int[numHashFunctions];
        long hash64 = Hashing.murmur3_128().hashObject(value, funnel).asLong();
        int hash1 = (int) hash64;
        int hash2 = (int) (hash64 >>> 32);
        for (int i = 1; i <= numHashFunctions; i++){
            int nextHash = hash1 + i * hash2;
            if (nextHash < 0){
                nextHash = ~nextHash;
            }
            offset[i -1] = nextHash % bitSize;
        }
        return offset;
    }


    /**
     * 计算bit数组长度
     * @param n
     * @param p
     * @return
     */
    private int optimalNumOfBits(long n, double p){
        if (p ==0){
            p = Double.MIN_VALUE;
        }
        return (int) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }


    /**
     * 计算Hash方法执行次数
     * @param n
     * @param m
     * @return
     */
    private int optimalNumOfHashFunctions(long n , long m){
        return Math.max(1, (int)Math.round((double) m / n * Math.log(2)));
    }


}
