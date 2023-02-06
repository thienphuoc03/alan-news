package com.htphuoc.alannewsserver.Repository;

import com.htphuoc.alannewsserver.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
