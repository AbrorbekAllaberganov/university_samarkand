package com.example.university.service.impl;

import com.example.university.entity.MyFile;
import com.example.university.entity.News;
import com.example.university.exceptions.ResourceNotFound;
import com.example.university.payload.NewsPayload;
import com.example.university.payload.Result;
import com.example.university.repository.NewsRepository;
import com.example.university.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final MyFileService myFileService;

    @Override
    public Result saveNews(NewsPayload newsPayload) {
        try {
            News news = new News();
            news.setImage(myFileService.findByHashId(newsPayload.getHashId()));
            news.setTitleUz(newsPayload.getTitleUz());
            news.setTitleRu(newsPayload.getTitleRu());
            news.setTitleEn(newsPayload.getTitleEn());
            news.setBodyUz(newsPayload.getBodyUz());
            news.setBodyRu(newsPayload.getBodyRu());
            news.setBodyEn(newsPayload.getBodyEn());

            newsRepository.save(news);
            return Result.success(news);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result editNews(UUID newsId, NewsPayload newsPayload) {
        try {
            News news = newsRepository.findById(newsId).orElseThrow(
                    () -> new ResourceNotFound("news", "id", newsId)
            );
            news.setImage(myFileService.findByHashId(newsPayload.getHashId()));
            news.setTitleUz(newsPayload.getTitleUz());
            news.setTitleRu(newsPayload.getTitleRu());
            news.setTitleEn(newsPayload.getTitleEn());
            news.setBodyUz(newsPayload.getBodyUz());
            news.setBodyRu(newsPayload.getBodyRu());
            news.setBodyEn(newsPayload.getBodyEn());

            newsRepository.save(news);
            return Result.success(news);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Result deleteNews(UUID newsId) {
        try {
            News news = newsRepository.findById(newsId).orElseThrow(
                    () -> new ResourceNotFound("news", "id", newsId)
            );
            newsRepository.deleteById(newsId);
            new File(String.format("%s/%s.%s", news.getImage().getUploadPath(), news.getImage().getHashId(), news.getImage().getExtension())).delete();

            return Result.message("successful deleted", true);
        } catch (Exception e) {
            return Result.exception(e);
        }
    }

    @Override
    public Page<News> getNewsWithPageable(int pageNo,int pageSize) {
        Pageable pageable= PageRequest.of(pageNo,pageSize, Sort.by(Sort.Direction.DESC,"createdAt"));
        return newsRepository.findAll(pageable);
    }
}
