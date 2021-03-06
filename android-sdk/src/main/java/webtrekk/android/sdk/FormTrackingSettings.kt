package webtrekk.android.sdk

/**
 * Created by Aleksandar Marinkovic on 2020-02-04.
 * Copyright (c) 2020 MAPP.
 *
 * @param formName form name can be activity name or changed
 * @param fieldIds only track specific elements of the view
 * @param renameFields rename specific fields
 * @param confirmButton clicked confirm or cancel button for the form
 * @param anonymous hide content for some critical fields
 * @param changeFieldsValue in some case is good to change value of the specific fields
 * @param pathAnalysis Can be used for path analysis, all filed will be processed in the given order
 */

data class FormTrackingSettings(
    var formName: String = "",
    var fieldIds: List<Int> = emptyList(),
    var renameFields: Map<Int, String> = emptyMap(),
    var changeFieldsValue: Map<Int, String> = emptyMap(),
    var anonymousSpecificFields: List<Int> = emptyList(),
    var fullContentSpecificFields: List<Int> = emptyList(),
    var confirmButton: Boolean = true,
    var anonymous: Boolean = true,
    var pathAnalysis: List<Int> = emptyList()
)