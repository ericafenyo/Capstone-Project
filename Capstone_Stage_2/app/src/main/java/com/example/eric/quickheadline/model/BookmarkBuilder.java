/*
 * Copyright (C) 2018 Eric Afenyo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.eric.quickheadline.model;

/**
 * Created by eric on 15/02/2018.
 */

public class BookmarkBuilder {
    private String source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;


    public BookmarkBuilder setSource(String source) {
        this.source = source;

        return this;
    }

    public BookmarkBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookmarkBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookmarkBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public BookmarkBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public BookmarkBuilder setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }

    public BookmarkBuilder setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }

    public Bookmark build(){
        return new Bookmark(source,author,title,description,url,urlToImage,publishedAt);
    }
}
