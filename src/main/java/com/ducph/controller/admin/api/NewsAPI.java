package com.ducph.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ducph.controller.model.NewsModel;
import com.ducph.service.INewsService;
import com.ducph.util.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = { "/api-admin-news" })
public class NewsAPI extends HttpServlet {
    @Inject
    private INewsService newsService;

    private static final long serialVersionUID = 3498428856033256652L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        newsModel = newsService.save(newsModel);
        mapper.writeValue(res.getOutputStream(), newsModel);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        newsModel = newsService.update(newsModel.getId(), newsModel);
        mapper.writeValue(res.getOutputStream(), newsModel);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        long[] ids = newsModel.getIds();
        long id = newsModel.getId();
        if (ids != null && ids.length != 0) {
            newsService.deleteByIds(ids);
        } else if (id != 0) {
            newsService.deleteById(id);
        }
        mapper.writeValue(res.getOutputStream(), "{}");
    }
}
