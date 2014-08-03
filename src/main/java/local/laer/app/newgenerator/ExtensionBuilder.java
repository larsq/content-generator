/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import local.laer.app.newgenerator.domain.SectionData;
import local.laer.app.newgenerator.domain.LayoutFormat;
import local.laer.app.newgenerator.domain.TileSection;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class ExtensionBuilder {
    public static String defaultExtension(LayoutFormat format) {
        return String.format("w%s-h%s", format.cols(), format.rows());
    }

    
    
    public static String defaultExtension(TileSection section) {
        String headlineExt = (section.getHeadline() == null) ? "nohead" : null;
        String contentExt = (section.getContent() == null) ? "notext" : null;
        String resourceExt = Optional.ofNullable(section.getResources())
                .map(Map::keySet)
                .map(set->String.join("-", set))
                .orElse(null);
        
        String ext = Lists.newArrayList(headlineExt, contentExt, resourceExt).stream()
                .filter(e->e != null)
                .collect(Collectors.joining("-"));
        
        if(Strings.isNullOrEmpty(ext)) {
            return "normal";
        } else {
            return ext;
        }
    }
}
