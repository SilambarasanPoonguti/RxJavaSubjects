package com.silambar.rxjavasubjects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import kotlinx.android.synthetic.main.fragment_rxjava_subject_demo.*

class RxJavaSubjectDemo : Fragment() {

  private lateinit var subjectType: SubjectType
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_rxjava_subject_demo, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    subjectType = arguments?.getSerializable(SUBJECT_TYPE) as SubjectType
    title.text = getString(subjectType.type)
    watchMovie.setOnClickListener { watchMovie() }
  }

  private fun watchMovie() {

    val movie =
        when (subjectType) {
          is SubjectType.Publish -> PublishSubject.create<Int>()
          is SubjectType.Replay -> ReplaySubject.create<Int>()
          is SubjectType.Behavior -> BehaviorSubject.create<Int>()
          is SubjectType.Async -> AsyncSubject.create<Int>()
        }

    movie.subscribe(karthik)

    movie.onNext(1)
    movie.onNext(2)
    movie.onNext(3)

    movie.subscribe(kathir)// Kathir also joined to watch this movie

    movie.onNext(4)
    movie.onNext(5)

    movie.subscribe(saravanan)//Saravanan also joined to watch this movie

    movie.onNext(6)
    movie.onNext(7)

    movie.onComplete()
  }

  private val karthik = object : Observer<Int> {
    override fun onComplete() {
      resultText.append(NEW_LINE)
      resultText.append(" Karthik watched movie ")
      resultText.append(NEW_LINE)
    }

    override fun onSubscribe(d: Disposable) {
      resultText.append(" Karthik joined to watch this movie")
      resultText.append(NEW_LINE)
    }

    override fun onNext(scene: Int) {
      resultText.append(" Karthik watching scene  $scene")
      resultText.append(NEW_LINE)
    }

    override fun onError(e: Throwable) {
      resultText.append(" Karthik has unable to watch scene, cause ${e.message}")
      resultText.append(NEW_LINE)
    }

  }

  private val kathir = object : Observer<Int> {
    override fun onComplete() {
      resultText.append(" Kathir watched movie ")
      resultText.append(NEW_LINE)
    }

    override fun onSubscribe(d: Disposable) {
      resultText.append(NEW_LINE)
      resultText.append(" Kathir also joined to watch this movie")
      resultText.append(NEW_LINE)
    }

    override fun onNext(scene: Int) {
      resultText.append(" Kathir watching scene  $scene")
      resultText.append(NEW_LINE)
    }

    override fun onError(e: Throwable) {
      resultText.append(" Kathir has unable to watch scene, cause ${e.message}")
      resultText.append(NEW_LINE)
    }
  }

  private val saravanan = object : Observer<Int> {
    override fun onComplete() {
      resultText.append(" Saravanan watched movie ")
      resultText.append(NEW_LINE)
    }

    override fun onSubscribe(d: Disposable) {
      resultText.append(NEW_LINE)
      resultText.append(" Saravanan also joined to watch this movie")
      resultText.append(NEW_LINE)
    }

    override fun onNext(scene: Int) {
      resultText.append(" Saravanan watching scene  $scene")
      resultText.append(NEW_LINE)
    }

    override fun onError(e: Throwable) {
      resultText.append(" Saravanan has unable to watch scene, cause ${e.message}")
      resultText.append(NEW_LINE)
    }
  }

  companion object {
    private const val NEW_LINE = "\n"
    const val SUBJECT_TYPE = "subject type"
    fun newInstance(subjectType: SubjectType): RxJavaSubjectDemo {
      return RxJavaSubjectDemo().apply {
        arguments = Bundle().apply {
          putSerializable(SUBJECT_TYPE, subjectType)
        }
      }
    }
  }

}