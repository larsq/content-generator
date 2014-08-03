/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.laer.app.newgenerator.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LayoutResource {
    @JsonProperty
    private final Map<LayoutFormat, String> uriMap;

    public LayoutResource(LayoutFormat format, String uri) {
        uriMap = ImmutableMap.of(format, uri);
    }

    @JsonCreator
    public LayoutResource(Map<LayoutFormat, String> uriMap) {
        this.uriMap = ImmutableMap.copyOf(uriMap);
    }
    
    public String uri(LayoutFormat format, boolean exact) {
        if(exact) {
            return uriMap.get(format);
        }
        
        LayoutFormat requested = LayoutFormat.matchBest(format, uriMap.keySet());
        return uriMap.get(requested);
    } 
    
    public Set<LayoutFormat> formats() {
        return uriMap.keySet();
    }
    
    public static LayoutResource with(LayoutFormat format, String uri) {
        return new LayoutResource(format, uri);
    }
    
    @Override
    public String toString() {
        return "Resource{" + "uriMap=" + uriMap + '}';
    }
    
}
