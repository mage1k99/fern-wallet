package com.fern.poll.impl.domain

import arrow.core.Either
import com.fern.domain.usecase.android.DeviceIdUseCase
import com.fern.poll.data.PollRepository
import com.fern.poll.data.model.PollId
import com.fern.poll.data.model.PollOptionId
import javax.inject.Inject

class VoteUseCase @Inject constructor(
  private val pollRepository: PollRepository,
  private val deviceIdUseCase: DeviceIdUseCase,
) {
  suspend fun vote(
    poll: PollId,
    option: PollOptionId,
  ): Either<String, Unit> {
    return pollRepository.vote(
      deviceId = deviceIdUseCase.getDeviceId(),
      poll = poll,
      option = option,
    ).onRight {
      pollRepository.setVoted(poll, voted = true)
    }
  }
}