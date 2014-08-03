/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.laer.app.newgenerator;

import java.util.HashMap;
import java.util.Map;
import static local.laer.app.newgenerator.ExtensionBuilder.defaultExtension;
import local.laer.app.newgenerator.domain.LayoutFormat;
import local.laer.app.newgenerator.domain.LayoutResource;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class LayoutResourceBuilder {
    public static LayoutResource build(String pattern, LayoutFormat... formats) {
        
        Map<LayoutFormat, String> resource = new HashMap<>();
        for(LayoutFormat format : formats) {
            resource.put(format, pattern.replace("{layout}", defaultExtension(format)));
        }
        return new LayoutResource(resource);
    }
}
