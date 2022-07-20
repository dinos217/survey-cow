import com.project.surveycow.dtos.QuestionResponseDto
import com.project.surveycow.entities.Answer
import com.project.surveycow.repositories.AnswerRepository
import com.project.surveycow.services.AnswerService
import com.project.surveycow.services.AnswerServiceImpl
import org.springframework.kafka.core.KafkaTemplate
import spock.lang.Specification

class AnswerServiceSpec extends Specification {

    AnswerRepository answerRepository = Mock(AnswerRepository)
    KafkaTemplate<String, Object> kafkaTemplate = Mock(KafkaTemplate)

    AnswerService service = new AnswerServiceImpl(answerRepository, kafkaTemplate)

    def "save a new answer in db"() {

        given: "a new response to a survey question"
        QuestionResponseDto questionResponseDto = new QuestionResponseDto()
        questionResponseDto.setUserId(1L)
        questionResponseDto.setSurveyId(1L)
        questionResponseDto.setQuestionId(1L)
        questionResponseDto.setIsLast(false)
        Answer answer = new Answer()

        when: "the repo save method is called"
        service.save(questionResponseDto)

        then:
        1 * answerRepository.existsByUserIdAndSurveyIdAndQuestionId(1L, 1L, 1L) >> false
        1 * answerRepository.save(_) >> answer
    }

    def "save a new answer in db in a survey already taken by user"() {

        given: "a new response to a survey question"
        QuestionResponseDto questionResponseDto = new QuestionResponseDto()
        questionResponseDto.setUserId(1L)
        questionResponseDto.setSurveyId(1L)
        questionResponseDto.setIsLast(false)

        when: "the repo save method is called"
        service.save(questionResponseDto)

        then:
        1 * answerRepository.existsAnswersByUserIdAndSurveyId(1L, 1L) >> true
        Exception ex = thrown()
        ex.getMessage() == "User with id: 1 has already taken the Survey with id: 1"
    }

    def "update an existing answer in db"() {

        Answer answerToUpdate = new Answer()
        answerToUpdate.setUserResponse("Updated response")

        given: "a new response to an already answered question"
        QuestionResponseDto questionResponseDto = new QuestionResponseDto()
        questionResponseDto.setUserId(1L)
        questionResponseDto.setSurveyId(1L)
        questionResponseDto.setQuestionId(1L)
        questionResponseDto.setUserResponse("Updated response")
        questionResponseDto.setIsLast(false)

        when: "the repo save method for update is called"
        service.update(questionResponseDto)

        then:
        1 * answerRepository.findByUserIdAndSurveyIdAndQuestionId(1L, 1L, 1L) >> answerToUpdate
        1 * answerRepository.save(_) >> answerToUpdate
    }

    def "delete previous answers"() {

        List<Answer> answersToDelete = new ArrayList<>()
        Answer answer1 = new Answer()
        answer1.setUserId(1L)
        answer1.setSurveyId(1L)
        answer1.setUserResponse("First")
        Answer answer2 = new Answer()
        answer2.setUserId(1L)
        answer2.setSurveyId(1L)
        answer2.setUserResponse("Second")
        answersToDelete.add(answer1)
        answersToDelete.add(answer2)

        given: "a user id and a survey id"
        QuestionResponseDto questionResponseDto = new QuestionResponseDto()
        questionResponseDto.setUserId(1L)
        questionResponseDto.setSurveyId(1L)

        when: "the user exits the survey before they finish it"
        service.deletePreviousAnswers(questionResponseDto)

        then: "delete the given answers until then"
        answerRepository.deleteAll(answersToDelete)
    }

}
