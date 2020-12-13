package a1.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PostingRepository extends CrudRepository<Posting, String> {
    List<Posting> findPostingsByDocDateGreaterThanEqualAndDocDateLessThanEqual(Date docDateStart, Date docDateEnd);
    List<Posting> findPostingsByIsAuthorizedAndDocDateGreaterThanEqualAndDocDateLessThanEqual(boolean authorized, Date docDateStart, Date docDateEnd);
}