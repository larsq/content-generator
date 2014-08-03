/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.laer.app.newgenerator.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TileSection {
    @JsonProperty
    private String headline;
    @JsonProperty
    private String content;
    @JsonProperty
    private Map<String, LayoutResource> resources;
    @JsonProperty
    private LayoutResource layouts;

    public TileSection() {
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, LayoutResource> getResources() {
        return resources;
    }

    public void setResources(Map<String, LayoutResource> resources) {
        this.resources = resources;
    }

    public void setResource(String name, LayoutResource resource) {
        this.resources = Maps.newHashMap();
        resources.put(name, resource);
    }

    public void setLayouts(LayoutResource layouts) {
        this.layouts = layouts;
    }

    public LayoutResource getLayouts() {
        return layouts;
    }

    @Override
    public String toString() {
        return "TileSection{" + "headline=" + headline + ", content=" + sizeOf(content) + "b, resources=" + resources + ", layouts=" + layouts + '}';
    }

    private int sizeOf(String content) {
        return content == null ? 0 : content.length();
    }



    
}
