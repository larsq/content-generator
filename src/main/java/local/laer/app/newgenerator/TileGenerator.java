/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator;

import com.google.common.base.CharMatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import local.laer.app.newgenerator.domain.LayoutFormat;
import local.laer.app.newgenerator.domain.SectionData;
import local.laer.app.newgenerator.domain.Tile;
import local.laer.app.newgenerator.domain.Tiles;

/**
 * Generate randomized variants of Tiles.
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class TileGenerator {

    private final ContentGenerator content = new ContentGenerator();
    private final TileBuilder articleBuilder = TileBuilder.build("laer-article");
    private final TileBuilder sectionBuilder = TileBuilder.build("laer-section");

    public Tile article(String sectionName) {
        LayoutFormat format = content.format();
        String headline = content.headline(5, 40);
        String text = content.content();

        SectionData article = new SectionData(null, headline, text);
        article.generateId();
        
        SectionData section = new SectionData(sectionName, sectionName);
        
        return articleBuilder.articleTile(section, article, format);
    }

    public Tile section() {
        CharMatcher filter = CharMatcher.anyOf(" :/-");
        LayoutFormat format = content.format();
        String headline = content.headline(5, 40);

        SectionData section = new SectionData();
        section.setHeadline(content.headline(5, 20));
        section.setId(filter.replaceFrom(section.getHeadline(),"_"));
        
        return sectionBuilder.sectionTile(section, LayoutFormat.HALF);
    }

    public List<Tile> sectionTiles(int n) {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tiles.add(section());
        }

        return tiles;
    }

    public List<Tile> articleTiles(int n, String section) {
        List<Tile> tiles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tiles.add(article(section));
        }

        return tiles;
    }
    
    public Tiles generate(int articlesPerSection, String... sections) {
        Tiles tiles = new Tiles();
        
        
        Arrays.stream(sections).forEach(str->tiles.addSection(sectionBuilder.sectionTile(new SectionData(str, str), LayoutFormat.HALF)));
        
        tiles.getSections().values()
                .forEach(s->s.setArticles(articleTiles(articlesPerSection, s.getSection().getId())));
        
        return tiles;
    }
}
