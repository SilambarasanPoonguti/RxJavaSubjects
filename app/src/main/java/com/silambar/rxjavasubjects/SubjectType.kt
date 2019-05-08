package com.silambar.rxjavasubjects

import androidx.annotation.StringRes
import java.io.Serializable

sealed class SubjectType : Serializable {
  @get:StringRes
  abstract val type: Int

  object Publish : SubjectType() {
    override val type: Int
      get() = R.string.type_publish
  }

  object Replay : SubjectType() {
    override val type: Int
      get() = R.string.type_replay
  }

  object Behavior : SubjectType() {
    override val type: Int
      get() = R.string.type_behavior
  }

  object Async : SubjectType() {
    override val type: Int
      get() = R.string.type_async
  }
}

