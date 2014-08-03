/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator;

import java.util.List;
import java.util.logging.Logger;
import local.laer.app.newgenerator.domain.Tile;
import local.laer.app.newgenerator.domain.Tiles;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class TileGeneratorTest {
    private Logger LOG = Logger.getLogger(getClass().getPackage().getName());
    private TileGenerator generator;

    public TileGeneratorTest() {
    }

    @Before
    public void initGenerator() {
        generator = new TileGenerator();

    }

    @Test
    public void testArticleGenerate() {
        List<Tile> tiles = generator.articleTiles(10, "foo");

        LOG.info(String.format("%s", tiles));
    }

    @Test
    public void testSectionGenerate() {
        List<Tile> tiles = generator.sectionTiles(10);

        LOG.info(String.format("%s", tiles));
    }

    @Test
    public void testTiles() {
        Tiles tiles = generator.generate(20, "foo", "bar", "woz");
        
        LOG.info(tiles.json());
        
    }
}
