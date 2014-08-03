/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import local.laer.app.newgenerator.domain.LayoutFormat;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class ContentGenerator {
    private final String[] lines = initLoremIspum();
    
    private final Random headlineLength = new Random();
    private final Randomizer<String> contentLine = Randomizer.uniformProbability(lines);
    private final Randomizer<LayoutFormat> layoutFormat = Randomizer.of(initRandomizer());
    private final Randomizer<Integer> contentSize = new Randomizer<>(new Integer[] {1,2,3,4,5,6,7,8,9,10}, new int[] {8,8,4,4,2,2});
    
    protected static String[] initLoremIspum() {
        try {
            try( InputStream in = ContentGenerator.class.getResourceAsStream("/LoremIspum.txt")) {
                return CharStreams.readLines(new InputStreamReader(in)).toArray(new String[0]);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected static Map<LayoutFormat, Integer> initRandomizer() {
        return ImmutableMap.<LayoutFormat, Integer>builder()
                .put(LayoutFormat.HALF, 16)
                .put(LayoutFormat.NORMAL, 4)
                .put(LayoutFormat.TALL, 4)
                .put(LayoutFormat.WIDE, 4)
                .put(LayoutFormat.EXTRA_TALL, 2)
                .put(LayoutFormat.EXTRA_WIDE, 2)
                .put(LayoutFormat.DOUBLE, 1)
                .build();
    }


    public LayoutFormat format() {
        return layoutFormat.next();
    }
    
    public String content() {
        int p = contentSize.next();
        
        return Arrays.stream(lines, 0, p).collect(Collectors.joining("\n"));
    }
    
    public String headline(int minLength, int maxLength) {
        String line = contentLine.next();

        int len = Math.min(minLength + headlineLength.nextInt(maxLength - minLength + 1), lines.length);
        
        return line.substring(0, len);
    }
}
