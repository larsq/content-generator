/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.laer.app.newgenerator.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import local.laer.app.newgenerator.LayoutResourceBuilder;

/**
 *
 * @author Lars Eriksson (larsq.eriksson@gmail.com)
 */
public class Nodes {
    private final static String PATTERN = "{prefix}-{layout}-{resource}-{name}";

    public static class TileNode extends AbstractNode<Tile> {
        private final Map<String, SectionNode> nodes = new HashMap<>();
        private final String id;
        private final String label;

        public TileNode(String prefix, SectionData section, SectionData article, LayoutFormat... formats) {
            super(prefix, "tile", String.join("-", section.getId(), article.getId()), formats);
            this.id = article.getId();
            this.label = article.getHeadline();
        }

        public TileNode(String prefix, SectionData section, LayoutFormat... formats) {
            super(prefix, "tile", section.getId(), formats);
            this.id = section.getId();
            this.label = section.getHeadline();
        }

        @Override
        public Tile get() {
            Tile tile = new Tile();
            tile.setId(id);
            tile.setName(label);
            tile.setFormats(formats);
            tile.setLayout(LayoutResourceBuilder.build(pattern(nodeId), formats));
            
            Map<String, TileSection> sections = nodes.entrySet().stream()
                    .collect(Collectors.toMap(Entry::getKey, e->e.getValue().get()));
            
            tile.setSections(sections);
            
            return tile;
        }

        public SectionNode section(SectionData article) {
            return nodes.computeIfAbsent(id, key -> new SectionNode(article, prefix, this, formats));
        }
    }

    public static class SectionNode extends AbstractNode<TileSection> {

        private final Map<String, LayoutResource> resources = new HashMap<>();
        private final SectionData value;

        SectionNode(SectionData value, String prefix, AbstractNode<Tile> parent, LayoutFormat... formats) {
            super(prefix, "section", String.join("-", parent.nodeId, value.getId()), formats);
            this.value = value;
        }

        @Override
        public TileSection get() {
            TileSection section = new TileSection();
            section.setHeadline(value.getHeadline());
            section.setContent(value.getContent());
            section.setLayouts(LayoutResourceBuilder.build(pattern(nodeId), formats));
            section.setResources(resources);

            return section;
        }

        public LayoutResource resource(String id, String extension, LayoutFormat... formats) {
            String resourceName;
            if(extension != null) {
                resourceName = String.join(".", pattern(id), extension);
            } else {
                resourceName = pattern(id);
            }
            
            
            
            return resources.compute(id, (k, v) -> LayoutResourceBuilder.build(resourceName, formats));
        }
    }

    public abstract static class AbstractNode<T> implements Supplier<T> {

        protected final LayoutFormat[] formats;
        protected final String prefix;
        protected final String resource;
        protected final String nodeId;

        public AbstractNode(String prefix, String resource, String name, LayoutFormat... formats) {
            this.prefix = prefix;
            this.resource = resource;
            this.nodeId = name;
            this.formats = formats;
        }

        @Override
        public abstract T get();

        protected String pattern(String givenName) {
            String result = PATTERN;

            result = result.replace("{prefix}", prefix);
            result = result.replace("{resource}", resource);
            result = result.replace("{name}", givenName);

            return result;
        }

    }
}
