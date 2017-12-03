package ru.luchenkova.rss76.data.api;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.List;

/**
 * Created by Tatyana Luchenkova on 28.11.2017.
 */
@Root(name = "rss", strict = false)
public class RSS {

    @Element(name = "channel")
    public Channel channel;

    @Override
    public String toString() {
        return "RSS{" +
                "channel=" + channel +
                '}';
    }

    // region Channel
    @Root(name = "channel")
    public static class Channel {

        @Path("title")
        @Text(required = false)
        public String title;

        @Path("link")
        @Text(required = false)
        public String link;

        @Path("description")
        @Text(required = false)
        public String description;

        @Path("image/url")
        @Text(required = false)
        public String image;

        @Path("pubDate")
        @Text(required = false)
        public String pubDate;

        @Path("lastBuildDate")
        @Text(required = false)
        public String lastBuildDate;

        @ElementList(inline = true, required = false)
        public List<Item> items;


        // region Item
        @Root(name = "item")
        public static class Item {

            @Path("guid")
            @Text(required = false)
            public String guid;

            @Path("title")
            @Text(required = false)
            public String title;

            @Path("link")
            @Text(required = false)
            public String link;

            @Path("pdalink")
            @Text(required = false)
            public String pdaLink;

            @Path("description")
            @Text(required = false)
            public String description;

            @Element(name = "enclosure", required = false)
            public Enclosure enclosure;

            @Path("pubDate")
            @Text(required = false)
            public String pubDate;

            @Path("category")
            @Text(required = false)
            public String category;


            @Override
            public String toString() {
                return "Item{" +
                        "title='" + title + '\'' +
                        ", link='" + link + '\'' +
                        ", pdaLink='" + pdaLink + '\'' +
                        ", description='" + description + '\'' +
                        ", enclosure='" + enclosure + '\'' +
                        ", pubDate='" + pubDate + '\'' +
                        ", category='" + category + '\'' +
                        '}';
            }

            // region Enclosure

            @Root(name = "enclosure")
            public static class Enclosure {

                @Attribute(name = "url")
                public String url;

                @Attribute(name = "length")
                public int length;

                @Attribute(name = "type")
                public String type;

                @Override
                public String toString() {
                    return "Enclosure{" +
                            "url='" + url + '\'' +
                            ", length=" + length +
                            ", type='" + type + '\'' +
                            '}';
                }
            }

            // endregion
        }

        // endregion Item


        @Override
        public String toString() {
            return "Channel{" +
                    "title='" + title + '\'' +
                    ", link='" + link + '\'' +
                    ", description='" + description + '\'' +
                    ", image='" + image + '\'' +
                    ", pubDate='" + pubDate + '\'' +
                    ", lastBuildDate='" + lastBuildDate + '\'' +
                    ", items=" + items +
                    '}';
        }
    }
    // endregion

}

