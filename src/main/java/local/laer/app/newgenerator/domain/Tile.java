/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tile {


    public Tile() {
    }

    @JsonProperty
    private String name;
    @JsonProperty
    private String id;
    @JsonProperty
    private LayoutResource layout;
    @JsonProperty
    private Map<String, TileSection> sections;
    @JsonProperty
    private Set<LayoutFormat> formats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LayoutResource getLayout() {
        return layout;
    }

    public void setLayout(LayoutResource layout) {
        this.layout = layout;
    }

    public Map<String, TileSection> getSections() {
        return sections;
    }

    public void setSections(Map<String, TileSection> sections) {
        this.sections = Maps.newHashMap(sections);
    }


    public Set<LayoutFormat> getFormats() {
        return formats;
    }

    public void setFormats(Set<LayoutFormat> formats) {
        
        this.formats = Sets.newHashSet(formats);
    }

    public void setFormats(LayoutFormat... formats) {
        this.formats = Sets.newHashSet(formats);
    }

    @Override
    public String toString() {
        return "Tile{" + "name=" + name + ", id=" + id + ", layout=" + layout + ", sections=" + sections + ", formats=" + formats + '}';
    }
    

}
