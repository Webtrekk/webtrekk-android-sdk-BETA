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

package webtrekk.android.sdk.extension

import android.app.Activity
import androidx.fragment.app.Fragment
import webtrekk.android.sdk.data.entity.TrackRequest
import webtrekk.android.sdk.util.appFirstStart
import webtrekk.android.sdk.util.currentSession

internal fun Activity.toTrackRequest(): TrackRequest =
    TrackRequest(
        name = this.localClassName,
        screenResolution = this.resolution(),
        fns = currentSession,
        one = appFirstStart
    )

internal fun Fragment.toTrackRequest(): TrackRequest =
    TrackRequest(
        name = this.javaClass.simpleName,
        screenResolution = this.context?.resolution(),
        fns = currentSession,
        one = appFirstStart
    )
