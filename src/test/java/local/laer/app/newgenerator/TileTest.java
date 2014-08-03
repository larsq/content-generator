/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator;

import java.util.logging.Logger;
import local.laer.app.newgenerator.domain.SectionData;
import local.laer.app.newgenerator.domain.LayoutFormat;
import local.laer.app.newgenerator.domain.Tile;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class TileTest {

    private static Logger LOG = Logger.getLogger(TileTest.class.getPackage().getName());
    private final TileBuilder builder = TileBuilder.build("laer");

    public TileTest() {
    }

    @Test
    public void testJsonSectionSerialize() {
        Tile tile = builder.sectionTile(new SectionData("fooId", "foo"));

        String json = TileBuilder.json(tile);

        LOG.info(json);
    }

    @Test
    public void testJsonArticleSerialize() {
        SectionData s = new SectionData("barId", "bar");
        SectionData a = new SectionData("fooId", "headline", "content");
        
        Tile tile = builder.articleTile(s, a, LayoutFormat.HALF);

        String json = TileBuilder.json(tile);

        LOG.info(json);
    }

}
