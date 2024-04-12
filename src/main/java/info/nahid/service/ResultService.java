package info.nahid.service;

import info.nahid.entity.Result;
import info.nahid.entity.Subject;

import java.util.List;

public interface ResultService {

    Result getStudentResults(Result result);

    List<Result> getAllResult();

    Result getAllStudentsAndSubjects();

}
