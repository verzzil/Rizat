package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.PaperDto;
import ru.itis.springbootdemo.dto.PapersPage;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.services.PapersService;

import java.util.List;

@Controller
public class PapersController {

    @Autowired
    private PapersService papersService;

    @GetMapping("/papers")
    public String getAllPapers(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("userId", userDetails.getUser().getId());
        }
        List<PaperDto> paperDtoList = papersService.findAllPapers();
        model.addAttribute("papers", paperDtoList);
        return "papers";
    }

    @GetMapping("/paper/create")
    public String getCreatePage() {
        return "create_post";
    }

    @PostMapping("/paper/create")
    public String createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, PaperDto paperDto) {
        paperDto.setAuthorId(userDetails.getUser().getId());
        papersService.save(paperDto);
        return "redirect:/papers";
    }

    @PostMapping("/paper/{paperId}/remove/{userId}")
    public String deletePost(@PathVariable("paperId") Long paperId, @PathVariable("userId") Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userId.equals(userDetails.getUser().getId())) {
            papersService.deleteById(paperId);
        }
        return "redirect:/papers";
    }

    @GetMapping("/paper/{paperId}/edit/{userId}")
    public String getEditPage(PaperDto paperDto, Model model, @PathVariable("paperId") Long paperId, @PathVariable("userId") Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userId.equals(userDetails.getUser().getId())) {
            model.addAttribute("paperId", paperId);
            model.addAttribute("userId", userId);
            model.addAttribute("paper", paperDto);
            return "edit_post";
        }
        return "redirect:/papers";
    }

    @PostMapping("/paper/{paperId}/edit/{userId}")
    public String editPost(PaperDto paperDto, @PathVariable("paperId") Long paperId, @PathVariable("userId") Long userId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userId.equals(userDetails.getUser().getId())) {
            papersService.editPaper(paperDto);
        }
        return "redirect:/papers";
    }

    @GetMapping("/papers/get-by-title")
    public ResponseEntity<List<PaperDto>> getAjaxPapers(@RequestParam(name = "template") String template) {
        return ResponseEntity.ok(papersService.findPapersByTemplate(template));
    }

    @GetMapping("/papers/search")
    @ResponseBody
    public ResponseEntity<PapersPage> search(@RequestParam("size") Integer size,
                                             @RequestParam("page") Integer page,
                                             @RequestParam(value = "q", required = false) String query,
                                             @RequestParam(value = "sort", required = false) String sort,
                                             @RequestParam(value = "direction", required = false) String direction) {
        return ResponseEntity.ok(papersService.search(size, page, query, sort, direction));
    }
}
