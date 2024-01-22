package com.ui.starshipUI.view;

import com.ui.starshipUI.Fetcher;
import com.ui.starshipUI.model.News.Article;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;


@PageTitle("News")
@Route(value = "News", layout = MainLayout.class)
@Uses(Icon.class)
@PermitAll
public class NewsView extends Div {

    private Grid<Article> grid;
    private String apiGatewayHost;

    public NewsView(@Value("${apiGateway.host}") String apiGatewayHost) {
        this.apiGatewayHost = apiGatewayHost;

        setSizeFull();
        addClassNames("news-view");

        VerticalLayout layout = new VerticalLayout(createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }

    private Component createGrid() {
        grid = new Grid<>(Article.class, false);
        // Add tooltip to the "title" column
        grid.addColumn("title").setAutoWidth(false).setTooltipGenerator(Article::getTitle);
        grid.addColumn("description").setAutoWidth(false).setTooltipGenerator(Article::getDescription);
        grid.addColumn("content").setAutoWidth(false).setTooltipGenerator(Article::getContent);
        // Render url as a clickable hyperlink
        grid.addColumn(new ComponentRenderer<>(article -> {
            if (!article.getUrl().isEmpty()) {
                Anchor anchor = new Anchor(article.getUrl(), "Article Link");
                anchor.setTarget("_blank"); // Open link in a new tab
                return anchor;
            } else {
                return new Anchor(); // Empty anchor if URL is empty
            }
        })).setHeader("Article URL").setAutoWidth(false);        grid.addColumn(new ComponentRenderer<>(article -> {
            if (!article.getImage().isEmpty()) {
                Image image = new Image(article.getImage(), "Image");
                image.setWidth("50px"); // Set the desired width
                image.setHeight("50px"); // Set the desired height
                return image;
            } else {
                return new Image(); // Empty image if URL is empty
            }
        })).setHeader("Image").setAutoWidth(false);


        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 'at' HH:mm:ss");

        grid.addColumn(article -> {
            if (article.getPublishedAt() != null) {
                LocalDateTime parsedDateTime = LocalDateTime.parse(article.getPublishedAt(), inputFormatter);
                return parsedDateTime.format(outputFormatter);
            } else {
                return "";
            }
        }).setHeader("Published At").setAutoWidth(true);


        grid.addColumn(new ComponentRenderer<>(article -> {
            if (!article.getSourceUrl().isEmpty()) {
                Anchor anchor = new Anchor(article.getSourceUrl(), article.getSourceName());
                anchor.setTarget("_blank"); // Open link in a new tab
                return anchor;
            } else {
                return new Anchor(); // Empty anchor if URL is empty
            }
        })).setHeader("Source URL").setAutoWidth(false);
        List<Article> articleList= new Fetcher<Article>().fetch(apiGatewayHost + "/api/news/top-headlines",
                Article.class, new HashMap<>(),
                (root) -> root);
        grid.setItems(articleList);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }

}
