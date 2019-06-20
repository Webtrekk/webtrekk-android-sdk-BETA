/*
 *  MIT License
 *
 *  Copyright (c) 2019 Webtrekk GmbH
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package webtrekk.android.sdk.domain.internal

import okhttp3.Request
import webtrekk.android.sdk.data.entity.DataTrack
import webtrekk.android.sdk.data.entity.TrackRequest
import webtrekk.android.sdk.data.repository.TrackRequestRepository
import webtrekk.android.sdk.datasource.SyncRequestsDataSource
import webtrekk.android.sdk.domain.InternalInteractor

class ExecuteRequest(
    private val trackRequestRepository: TrackRequestRepository,
    private val syncRequestsDataSource: SyncRequestsDataSource<DataTrack>
) : InternalInteractor<ExecuteRequest.Params, DataTrack> {

    override suspend operator fun invoke(invokeParams: Params): Result<DataTrack> {
        return with(invokeParams) {
            // Send the request and update its state if the request sent successfully or failed.
            syncRequestsDataSource.sendRequest(request, dataTrack)
                .onSuccess {
                    it.trackRequest.requestState = TrackRequest.RequestState.DONE
                    trackRequestRepository.updateTrackRequests(it.trackRequest)
                }
                .onFailure {
                    dataTrack.trackRequest.requestState = TrackRequest.RequestState.FAILED
                    trackRequestRepository.updateTrackRequests(dataTrack.trackRequest)
                }
        }
    }

    data class Params(val request: Request, val dataTrack: DataTrack)
}