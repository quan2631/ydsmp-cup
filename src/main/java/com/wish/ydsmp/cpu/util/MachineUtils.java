package com.wish.ydsmp.cpu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import com.sun.management.OperatingSystemMXBean;

public class MachineUtils {

    /**
     * 判断服务是否运行
     * @param ProcessName 启动这个服务的进程名 带.exe
     * @param ServiceName 服务名
     * @return 返回运行状态
     */
    public static Boolean GetServiceStatue(String ProcessName,String ServiceName) {
        String temp = "";
        InputStream inputStream=null;
        BufferedReader bufferedReader=null;
        try {
            Process process = Runtime.getRuntime().exec("tasklist /svc");
            inputStream  = process.getInputStream();
            bufferedReader  = new BufferedReader(new InputStreamReader(inputStream,"GBK"));//注意中文编码问题
            while ((temp=bufferedReader.readLine())!=null){
                if(temp != null && temp.contains(ServiceName) && temp.contains(ProcessName) ) {
                    bufferedReader.close();
                    inputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader!=null) {
                    bufferedReader.close();
                }
                if(inputStream!=null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 判断服务是否安装
     * @param ServiceName 服务名
     * @return 
     */
    public static Boolean GetServiceInstallStatue(String ServiceName) {
        String temp = "";
        InputStream inputStream = null;
        BufferedReader bufferedReader=null;
        try {
            Process process = Runtime.getRuntime().exec("sc query "+ServiceName);//查询服务是否安装
            inputStream = process.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));//注意中文编码问题
            while ((temp=bufferedReader.readLine())!=null){
                if(temp !=null && temp.contains(ServiceName)) {
                    bufferedReader.close();
                    inputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader!=null) {
                    bufferedReader.close();
                }
                if(inputStream!=null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * @param cmd cmd命令或者bat文件，bat文件获取系统权限时会有闪屏
     * 获取系统权限的方法 
     *  @echo off
        %1 mshta vbscript:CreateObject("Shell.Application").ShellExecute("cmd.exe","/c %~s0 ::","","runas",1)(window.close)
        cd /d "%~dp0"
        ipconfig
     * @return 命令输出内容
     * 
     */
    public static String executeCmd(String cmd) {
        String line = null;
        BufferedReader br=null;
        InputStream inputStream=null;
        StringBuffer buffer=new StringBuffer();
        try {
            Process proc = Runtime.getRuntime().exec(cmd);// 执行命令
            inputStream = proc.getInputStream();//执行结果 得到进程的标准输出信息流
            br = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
            while ((line = br.readLine()) != null) {
                buffer.append(line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(br!=null) {
                    br.close();
                }
                if(inputStream!=null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    /**
     * 获取mac地址
     * @return
     */
    public static String getLocalMac()  {
        StringBuffer sb = new StringBuffer();
        try {
            InetAddress ia = InetAddress.getLocalHost();
            byte[]    mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            for(int i=0; i<mac.length; i++) {
                if(i!=0) {
                    sb.append("-");
                }
                int temp = mac[i]&0xff;//字节转换为整数
                String str = Integer.toHexString(temp);
                if(str.length()==1) {
                    sb.append("0"+str);
                }else {
                    sb.append(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 获取操作系统类型
     * @return
     */
    public static String getOsType()  {
        Properties props=System.getProperties(); //获得系统属性集  
        String osName = props.getProperty("os.name"); //操作系统名称  
        //String osArch = props.getProperty("os.arch"); //操作系统构架  
        //String osVersion = props.getProperty("os.version"); //操作系统版本 
        return osName;
    }

    /**
     * 获取本地IP
     * @return
     */
    public static String getLocalIP(){
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); 
            while (en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()  && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        sb.append(inetAddress.getHostAddress().toString()+"\n");
                    }
                }
            }
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 获取外网ip和所在地
     * @return
     */
    public static String getRemoteIp() {
        InputStream in = null;
        StringBuffer buffer = new StringBuffer();
        try {
            //             URL url = new URL("http://www.ip138.com/ip2city.asp"); //创建 URL
            URL url = new URL("http://ip.chinaz.com/getip.aspx"); //创建 URL
            in = url.openStream(); // 打开到这个URL的流
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null)   
            {
                buffer.append(inputLine);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally { 
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    /**
     * 获取最大可用磁盘
     * @return 返回  C D E F....
     */
    public static String getMaxDisk()  {
        long size=0;
        String max="";
        File[] roots = File.listRoots();  
        for (File file : roots) {  
            if( file.getFreeSpace()>size) {
                size=file.getFreeSpace();
                max=file.getPath().substring(0, 1);
            }
            System.out.println("Free space = " + (file.getFreeSpace()/(1024*1024))/1024);  //显示GB大小
            System.out.println("Usable space = " + (file.getUsableSpace()/(1024*1024))/1024);
            System.out.println("Total space = " + (file.getTotalSpace()/(1024*1024))/1024);
            System.out.println("used space  = " + ((file.getTotalSpace()-file.getFreeSpace())/(1024*1024))/1024);
            System.out.println();
        }  
        System.out.println(max);  
        return max;
    }

    /**
     * 获取当前运行路径
     * @return
     */
    public static String  getPath() {
        File directory = new File("");//设定为当前文件夹File directory = new File("..")
        String path="";
        try{
            path=directory.getCanonicalPath();//获取标准的路径
            //path=directory.getAbsolutePath();//获取绝对路径
        }catch(Exception e){
            
        }
        return path;
    }
    
    /**
     * 获取内存使用率
     * @return
     */
    public static String getMemery() {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();// 总的物理内存+虚拟内存
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();    // 剩余的物理内存
        Double compare = (Double) (1 - freePhysicalMemorySize * 1.0 / totalvirtualMemory) * 100;
        String str = "内存使用率:" + compare.intValue() + "%";
        return str;
    }

    /**
     * 获取CPU使用率
     * @return
     */
    public static String getCpuRatio() {
        try {
            String procCmd = System.getenv("windir") + "//system32//wbem//wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
            long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));    // 取进程信息
            Thread.sleep(200);
            long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
            if (c0 != null && c1 != null) {
                long idletime = c1[0] - c0[0];
                long busytime = c1[1] - c0[1];
                return "CPU使用率:"+ Double.valueOf(100 * (busytime) * 1.0 / (busytime + idletime)).intValue() + "%";
            } else {
                return "CPU使用率:" + 0 + "%";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "CPU使用率:" + 0 + "%";
        }
    }    
    private static long[] readCpu(final Process proc) {
        long[] retn = new long[2];
        try {
            proc.getOutputStream().close();
            InputStreamReader ir = new InputStreamReader(proc.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line = input.readLine();
            if (line == null || line.length() < 10) {
                return null;
            }
            int capidx = line.indexOf("Caption");
            int cmdidx = line.indexOf("CommandLine");
            int rocidx = line.indexOf("ReadOperationCount");
            int umtidx = line.indexOf("UserModeTime");
            int kmtidx = line.indexOf("KernelModeTime");
            int wocidx = line.indexOf("WriteOperationCount");
            long idletime = 0;
            long kneltime = 0;
            long usertime = 0;
            while ((line = input.readLine()) != null) {
                if (line.length() < wocidx) {
                    continue;
                }
                String caption = substring(line, capidx, cmdidx - 1).trim();
                String cmd = substring(line, cmdidx, kmtidx - 1).trim();
                if (cmd.indexOf("wmic.exe") >= 0) {
                    continue;
                }
                String s1 = substring(line, kmtidx, rocidx - 1).trim();
                String s2 = substring(line, umtidx, wocidx - 1).trim();
                if (caption.equals("System Idle Process") || caption.equals("System")) {
                    if (s1.length() > 0)
                        idletime += Long.valueOf(s1).longValue();
                    if (s2.length() > 0)
                        idletime += Long.valueOf(s2).longValue();
                    continue;
                }
                if (s1.length() > 0)
                    kneltime += Long.valueOf(s1).longValue();
                if (s2.length() > 0)
                    usertime += Long.valueOf(s2).longValue();
            }
            retn[0] = idletime;
            retn[1] = kneltime + usertime;
            return retn;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                proc.getInputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }    
    private static String substring(String src, int start_idx, int end_idx) {
        byte[] b = src.getBytes();
        String tgt = "";
        for (int i = start_idx; i <= end_idx; i++) {
            tgt += (char) b[i];
        }
        return tgt;
    }
    
}