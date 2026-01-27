package com.jobportal.backend.service;

import com.jobportal.backend.dto.CategoryRequest;
import com.jobportal.backend.dto.CategoryResponse;
import com.jobportal.backend.entity.JobCategory;
import com.jobportal.backend.entity.Role;
import com.jobportal.backend.entity.User;
import com.jobportal.backend.exception.BadRequestException;
import com.jobportal.backend.exception.NotFoundException;
import com.jobportal.backend.repository.JobCategoryRepository;
import com.jobportal.backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final JobCategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public CategoryService(JobCategoryRepository categoryRepository,
                           UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public CategoryResponse create(CategoryRequest request) {
        User current = getCurrentUser();
        if (current.getRole() != Role.ADMIN) {
            throw new BadRequestException("Only admin can create categories");
        }

        categoryRepository.findByName(request.getName()).ifPresent(c -> {
            throw new BadRequestException("Category already exists");
        });

        JobCategory category = JobCategory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        categoryRepository.save(category);
        return toResponse(category);
    }

    public List<CategoryResponse> listAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private CategoryResponse toResponse(JobCategory c) {
        return CategoryResponse.builder()
                .id(c.getId())
                .name(c.getName())
                .description(c.getDescription())
                .build();

    }

}
