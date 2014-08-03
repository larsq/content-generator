/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package local.laer.app.newgenerator.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tiles{ 
    @JsonProperty
    private Map<String, SectionStructure> sections;
    
    public Tiles() {
    }

    public Map<String, SectionStructure> getSections() {
        return sections;
    }

    public void setSections(Map<String, SectionStructure> sections) {
        this.sections = sections;
    }

    
    public SectionStructure addSection(Tile tile) {
        if(sections == null) {
            sections = new HashMap<>();
        }
        
        return sections.computeIfAbsent(tile.getId(), __->new SectionStructure(tile));
    }
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SectionStructure {
        @JsonProperty
        private Tile section;
        @JsonProperty
        private List<Tile> articles;

        public SectionStructure() {
        }

        public SectionStructure(Tile section) {
            this.section = section;
        }

        
        
        public Tile getSection() {
            return section;
        }

        public void setSection(Tile section) {
            this.section = section;
        }

        public List<Tile> getArticles() {
            return articles;
        }

        public void setArticles(List<Tile> articles) {
            this.articles = articles;
        }
        
    }
    
    public String json() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Tiles.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
}
