package org.zerogravitysolutions.subject;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubjectService {
    ResponseEntity<SubjectDto> save(SubjectDto subjectDto);

    ResponseEntity<List<SubjectDto>> findAll();

    ResponseEntity<SubjectDto> findById(Long id);

    ResponseEntity<SubjectDto> update(SubjectDto subjectDto);

    ResponseEntity<SubjectDto> partialUpdate(Long id, SubjectDto subjectDto);

    void deleteById(Long id);
}
