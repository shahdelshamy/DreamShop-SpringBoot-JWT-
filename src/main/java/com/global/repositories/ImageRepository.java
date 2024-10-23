package com.global.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.global.models.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}
