package com.wish.ydsmp.cpu.vo;

/**
 * @author: QUAN
 * @date: Created in 2019/12/11 10:43
 * @description: 磁盘使用情况
 * @modified By:
 */
public class DiskUsage {

    private String freeSpace;

    private String usableSpace;

    private String totalSpace;

    private String usedSpace;

    public String getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(String freeSpace) {
        this.freeSpace = freeSpace;
    }

    public String getUsableSpace() {
        return usableSpace;
    }

    public void setUsableSpace(String usableSpace) {
        this.usableSpace = usableSpace;
    }

    public String getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(String totalSpace) {
        this.totalSpace = totalSpace;
    }

    public String getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(String usedSpace) {
        this.usedSpace = usedSpace;
    }

    public DiskUsage() {}

    public DiskUsage(String freeSpace, String usableSpace, String totalSpace, String usedSpace) {
        this.freeSpace = freeSpace;
        this.usableSpace = usableSpace;
        this.totalSpace = totalSpace;
        this.usedSpace = usedSpace;
    }

    @Override
    public String toString() {
        return "DiskUsage{" +
                "freeSpace='" + freeSpace + '\'' +
                ", usableSpace='" + usableSpace + '\'' +
                ", totalSpace='" + totalSpace + '\'' +
                ", usedSpace='" + usedSpace + '\'' +
                '}';
    }
}
