package com.example.restfulapp.dao;

import com.example.restfulapp.entity.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Repository
public class GenreDaoImpl extends AbstractCrudDao<Genre> implements GenreDao {
    public GenreDaoImpl() {
        super(Genre.class);
    }

    @Transactional
    @Override
    public Genre patchUpdate(Genre genre) {
        Genre current = get(genre.getId());
        if (StringUtils.hasText(genre.getDescription())) {
            current.setDescription(genre.getDescription());
        }
        return update(current);
    }
}
