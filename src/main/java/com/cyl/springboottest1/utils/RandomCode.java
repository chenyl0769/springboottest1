package com.cyl.springboottest1.utils;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

public class RandomCode {

    private int width=120;
    private int height=40;
    private int codecount=4;
    private String code=null;
    private BufferedImage bufferedImage = null;
    private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8',
            '9' };
    private Random random = new Random();

    public RandomCode() {

    }

    public BufferedImage createcode() {
        bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setBackground(Color.BLACK);

        graphics2D.fillRect(0,0,width,height);
        graphics2D.setColor(Color.WHITE);


        StringBuffer stringBuffer = new StringBuffer();

        for (int i=0;i<codecount;i++) {
            //设置颜色
            graphics2D.setColor(Color.BLACK);
            //随机数组下标
            int codestatu= random.nextInt(codeSequence.length);
            //追加stringbuffer
            stringBuffer.append(codeSequence[codestatu]);
            graphics2D.setFont(new Font("微软雅黑", Font.BOLD, (random.nextInt(3)+1)*20));
            graphics2D.drawString(String.valueOf(codeSequence[codestatu]),i*30,40);
            graphics2D.drawLine(0,0,120,40);
        }
        code=stringBuffer.toString();
        graphics2D.dispose();
        return bufferedImage;
    }

    public String getCode() {
        return code;
    }
}
