package com.wish.ydsmp.cpu.test;

import com.wish.ydsmp.cpu.util.MachineUtils;

/**
 * @author: QUAN
 * @date: Created in 2019/12/11 10:23
 * @description:
 * @modified By:
 */
public class HelloTest {
    public static void main(String[] args) {

        System.out.println("操作系统类型：" + MachineUtils.getOsType());
        System.out.println("IP地址（本地）：" + MachineUtils.getLocalIP());
        System.out.println("磁盘使用：" + MachineUtils.getMaxDisk());
        System.out.println("内存使用（物理内存）：" + MachineUtils.getMemery());
        System.out.println("CUP使用率：" + MachineUtils.getCpuRatio());

    }
}
