package com.dafa.qipai.dafaqipai.util;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketUtils {


    public static void connectServerWithTCPSocket() {

        Socket socket;
        try {// 创建一个Socket对象，并指定服务端的IP及端口号
            socket = new Socket("43.230.171.202", 8080);
            // 创建一个InputStream用户读取要发送的文件。
//            InputStream inputStream = new FileInputStream("e://a.txt");
//            // 获取Socket的OutputStream对象用于发送数据。
//            OutputStream outputStream = socket.getOutputStream();
//            // 创建一个byte类型的buffer字节数组，用于存放读取的本地文件
//            byte buffer[] = new byte[4 * 1024];
//            int temp = 0;
//            // 循环读取文件
//            while ((temp = inputStream.read(buffer)) != -1) {
//                // 把数据写入到OuputStream对象中
//                outputStream.write(buffer, 0, temp);
//            }
//            // 发送读取的数据到服务端
//            outputStream.flush();

            /** 或创建一个报文，使用BufferedWriter写入,看你的需求 **/
          String socketData = "[2143213;21343fjks;213]";
          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
          writer.write(socketData.replace("\n", " ") + "\n");
          writer.flush();
            /************************************************/
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void ReceiveServerSocketData() {
        DatagramSocket socket;
        try {
            //实例化的端口号要和发送时的socket一致，否则收不到data
            socket = new DatagramSocket(8080);
            byte data[] = new byte[4 * 1024];
            //参数一:要接受的data 参数二：data的长度
            DatagramPacket packet = new DatagramPacket(data, data.length);
            socket.receive(packet);
            //把接收到的data转换为String字符串
            String result = new String(packet.getData(), packet.getOffset(),
                    packet.getLength());
            socket.close();//不使用了记得要关闭
            System.out.println("the number of reveived Socket is  :" + "" + "udpData:" + result);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
