/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CharMatcher;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.laer.app.newgenerator.domain.LayoutFormat;
import local.laer.app.newgenerator.domain.Nodes;
import local.laer.app.newgenerator.domain.SectionData;
import local.laer.app.newgenerator.domain.Tile;

/**
 * Builder for defining tiles
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class TileBuilder {

    
    public static TileBuilder build(String prefix) {
        return new TileBuilder(prefix);
    }

    public static String json(Tile tile) {
        try {
            return new ObjectMapper().writeValueAsString(tile);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(TileBuilder.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }
    private final String prefix;
    private final CharMatcher unsafe = CharMatcher.anyOf(":/ -");

    public TileBuilder(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Define a standard section tile
     *
     * @param section
     * @param formats the format that should be defined. At least one format
     * should be specified.
     * @return a section tile
     */
    public Tile sectionTile(SectionData section, LayoutFormat... formats) {
        Nodes.TileNode sectionNode = new Nodes.TileNode(prefix, section, formats);
        sectionNode.section(new SectionData("cover", section.getHeadline())).resource(section.getId(), "png", formats);
        
        return sectionNode.get();
    }




    public Tile articleTile(SectionData section, SectionData article, LayoutFormat... formats) {
        Nodes.TileNode articleNode = new Nodes.TileNode(prefix, section, article, formats);
        articleNode.section(new SectionData("cover", article.getHeadline(), null)).resource(String.join("-", "cover", article.getId()), "png", formats);
        articleNode.section(new SectionData("story", article.getHeadline(), article.getContent())).resource(String.join("-", "story", article.getId()), "png", formats);
        return articleNode.get();
    }
}
