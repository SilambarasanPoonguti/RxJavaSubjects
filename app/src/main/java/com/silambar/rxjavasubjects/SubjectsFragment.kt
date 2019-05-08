package com.silambar.rxjavasubjects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_rxjava_subjects.asyncSubject
import kotlinx.android.synthetic.main.fragment_rxjava_subjects.behaviorSubject
import kotlinx.android.synthetic.main.fragment_rxjava_subjects.publishSubject
import kotlinx.android.synthetic.main.fragment_rxjava_subjects.replaySubject

class SubjectsFragment : Fragment(), View.OnClickListener  {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_rxjava_subjects, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    publishSubject.setOnClickListener(this)
    replaySubject.setOnClickListener(this)
    behaviorSubject.setOnClickListener(this)
    asyncSubject.setOnClickListener(this)
  }

  override fun onClick(v: View?) {
    val subjectType: SubjectType =
        when (v) {
          publishSubject -> SubjectType.Publish
          replaySubject -> SubjectType.Replay
          behaviorSubject -> SubjectType.Behavior
          asyncSubject -> SubjectType.Async
          else -> SubjectType.Publish
        }

    fragmentManager?.beginTransaction()
        ?.replace(R.id.container, RxJavaSubjectDemo.newInstance(subjectType))
        ?.addToBackStack(null)
        ?.commit()
  }
}