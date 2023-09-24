package com.production.quizharrypotterapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.production.quizharrypotterapp.db.Player
import com.production.quizharrypotterapp.db.Question
import com.production.quizharrypotterapp.db.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

const val INITIAL_TIME = 30
const val INITIAL_SCORE = 0
const val COUNT_QUETION = 0
const val INITIAL_SELECT = -1

@HiltViewModel
class ViewModelGameQuiz @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // Storage of questions and answers
    private val _questionAndAnswers = MutableLiveData<List<Question>>()

    // Questions and answers that have not been used and are available for display
    private var questionDisplayed: MutableList<Question> = mutableListOf()

    // Question to display on screen
    private val _question = MutableLiveData<String>()
    val question: LiveData<String> = _question

    // Answers to display on screen
    private val _answers = MutableLiveData<List<String>>()
    val answers: LiveData<List<String>> = _answers

    private val _namePlayer = mutableStateOf("")

    // Value assigned to the correct answer
    private val _correctQuestion = MutableLiveData<Int>()

    // Questions counter
    private val _countQuestion = MutableLiveData<Int>()
    val countQuestion: LiveData<Int> = _countQuestion

    // Value of the answer selected by the player
    private val _selectAnswerPlayer = MutableLiveData<Int>()
    val selectAnswerPlayer: LiveData<Int> = _selectAnswerPlayer

    // Value to indicate when it is necessary to show the dialog box
    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    // Numeric value of time to display on screen
    private val _timeLeft = MutableLiveData<Int>()
    val timeLeft: LiveData<Int> = _timeLeft

    // Player Score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> = _score

    // Value that indicates when to generate the animation
    private val _animateText = MutableLiveData<Boolean>()
    val animateText: LiveData<Boolean> = _animateText

    private val _playersList = MutableLiveData<List<Player>>()
    val playersList: LiveData<List<Player>> = _playersList

    // Clock generator
    private var timerJob: Job? = null

    init {
        reset()
    }

    fun starTimer() {
        questionRandom()

        timerJob = viewModelScope.launch {
            while (_timeLeft.value!! > 0) {
                delay(1000)
                _timeLeft.value = _timeLeft.value!! - 1
            }
        }
    }

    fun updateSelectAnswerPlayer(newValue: Int) {
        _selectAnswerPlayer.value = newValue
    }

    private fun stopTimer() {
        timerJob?.cancel()
    }

    fun setNamePlayer(name: String) {
        _namePlayer.value = name
    }

    fun answerCorrect() {
        stopTimer()
        if (_selectAnswerPlayer.value == _correctQuestion.value) {
            _score.value = _score.value?.plus(_timeLeft.value!!)
        }
        _selectAnswerPlayer.value = INITIAL_SELECT
        _countQuestion.value = _countQuestion.value?.plus(1)

        if (_countQuestion.value!! <= 10) {
            _timeLeft.value = INITIAL_TIME
            starTimer()
        } else {
            _showDialog.value = true
            val player = Player(player = _namePlayer.value, score = _score.value!!)
            viewModelScope.launch {
                repository.addPlayer(player)
                delay(1000)
                _playersList.value = repository.getRanking()
            }
        }


    }

    private fun questionRandom() {
        val allQuestions = _questionAndAnswers.value?.toMutableList()
        if (questionDisplayed.size == allQuestions?.size) {
            questionDisplayed.clear()
        }
        val questionAvailable = allQuestions?.minus(questionDisplayed.toSet())

        if (questionAvailable != null) {
            if (questionAvailable.isEmpty()) {
                questionDisplayed.clear()
            }
        }

        val randomQuestion = questionAvailable?.random()
        if (randomQuestion != null) {
            questionDisplayed.add(randomQuestion)
        }
        if (randomQuestion != null) {
            _question.value = randomQuestion.question
        }
        if (randomQuestion != null) {
            _answers.value = randomQuestion.optionsAnswer
        }
        if (randomQuestion != null) {
            _correctQuestion.value = randomQuestion.correctAnswer
        }
    }

    fun cancelDialog() {
        _showDialog.value = false
        reset()
    }

    private fun reset() {
        _questionAndAnswers.value = repository.question
        _selectAnswerPlayer.value = INITIAL_SELECT
        _timeLeft.value = INITIAL_TIME
        _score.value = INITIAL_SCORE
        _countQuestion.value = COUNT_QUETION
    }
}