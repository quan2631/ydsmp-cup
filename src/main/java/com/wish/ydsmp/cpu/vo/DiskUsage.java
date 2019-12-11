package com.wish.ydsmp.cpu.vo;

/**
 * @author: QUAN
 * @date: Created in 2019/12/11 10:43
 * @description: 磁盘使用情况
 * @modified By:
 */
public class DiskUsage {
    /**
     * 盘符
     */
    private String name;
    /**
     * 可用空间
     */
    private String freeSpace;
    /**
     * 可使用空间（同可用空间）
     */
    private String usableSpace;
    /**
     * 总空间
     */
    private String totalSpace;
    /**
     * 已使用空间
     */
    private String usedSpace;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
