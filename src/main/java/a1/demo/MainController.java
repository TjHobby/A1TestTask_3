package a1.demo;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)

public class MainController {

    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private PostingRepository postingRepository;

    List<List<String>> logins = CSVUtils.parseCSV("logins.csv",",");
    List<List<String>> postings = CSVUtils.parseCSV("postings.csv", ";");
    boolean columnAddedResult = CSVUtils.addIsAuthorizedColumn(postings,logins);

    @GetMapping(path="/add_logins")
    public @ResponseBody
    String addLogins () {
        List<Login> loginsList = CSVUtils.parseLogin(logins);
        for(Login l: loginsList)
            loginRepository.save(l);
        return "Saved logins!";
    }

    @GetMapping(path="/add_postings")
    public @ResponseBody
    String addPostings () throws ParseException {
        List<Posting> postingList = CSVUtils.parsePostings(postings);
        for(Posting p : postingList)
            postingRepository.save(p);
        return "Saved postings!";
    }

    @GetMapping(path="/all")
    public @ResponseBody String getAllLogins() {
        return new Gson().toJson(loginRepository.findAll());
    }

    @GetMapping(path ="/get_postings")
    public @ResponseBody String findPostings(@RequestParam String start, @RequestParam String end, @RequestParam(required = false) String authorized) throws ParseException {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = df.parse(start);
        Date endDate = df.parse(end);
        if(authorized == null)
            return new Gson().toJson(postingRepository.findPostingsByDocDateGreaterThanEqualAndDocDateLessThanEqual(startDate, endDate));
        return new Gson().toJson(postingRepository.findPostingsByIsAuthorizedAndDocDateGreaterThanEqualAndDocDateLessThanEqual(Boolean.parseBoolean(authorized),startDate,endDate));
    }
}