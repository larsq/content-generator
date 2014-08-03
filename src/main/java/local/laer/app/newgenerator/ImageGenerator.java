/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.laer.app.newgenerator;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import local.laer.app.newgenerator.domain.LayoutFormat;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class ImageGenerator {
    private final Randomizer<String> images = Randomizer.uniformProbability(null, "i/w1-h2.png","i/w2-h1.png","i/w2-h2.png","i/w4-h1.png","i/w4-h2.png");
    
    private final static String FILENAME_FORMAT_REGEXP = "w(\\d)-h(\\d)";
    private static Set<LayoutFormat> availableFormatByConvention(String filename) {
        if(filename == null) {
            return ImmutableSet.of();
        }
        
        Matcher m = Pattern.compile(FILENAME_FORMAT_REGEXP).matcher(filename);
        if(m.find()) {
            int cols = Integer.parseInt(m.group(1));
            int rows = Integer.parseInt(m.group(2));
            
            return LayoutFormat.covers(rows, cols);
        }
        
        return ImmutableSet.of();
    }
    
    public String image(LayoutFormat format) {
        return images.stream()
                .limit(20)
                .filter(str->availableFormatByConvention(str).contains(format))
                .findFirst().orElse(null);
    }
}
