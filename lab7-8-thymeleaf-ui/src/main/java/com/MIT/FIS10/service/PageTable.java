package com.MIT.FIS10.service;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageTable<T> {

    private List<T> content;     // List of items on the current page
    private int currentPage;     // Current page number
    private int totalPages;      // Total number of pages
    private long totalItems;     // Total number of items (users)

    // Constructor
    public PageTable(Page<T> page) {
        this.content = page.getContent();  // Get content for the current page
        this.currentPage = page.getNumber() + 1; // Spring data pages are 0-indexed, so add 1 for display
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getTotalElements();
    }

    // Getters and Setters
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
}
