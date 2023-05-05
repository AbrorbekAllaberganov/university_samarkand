package com.example.university.service;

import com.example.university.entity.News;
import com.example.university.payload.NewsPayload;
import com.example.university.payload.Result;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface NewsService {
    Result saveNews(NewsPayload newsPayload);

    Result editNews(UUID newsId, NewsPayload newsPayload);

    Result deleteNews(UUID newsId);

    Page<News> getNewsWithPageable(int PageNo,int pageSize);

    Result findById(UUID newsId);

}
