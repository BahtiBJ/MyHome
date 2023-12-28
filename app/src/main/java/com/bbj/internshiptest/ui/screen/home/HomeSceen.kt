package com.bbj.internshiptest.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bbj.internshiptest.R
import com.bbj.internshiptest.ui.screen.home.model.Section
import com.bbj.internshiptest.ui.theme.BlueF4
import com.bbj.internshiptest.ui.theme.Typography

@Composable
fun HomeScreen(
    selectedSection: Section,
    onSectionClick : (Section) -> Unit
) {
    Column() {
        Text(
            text = stringResource(id = R.string.my_home), style = Typography.titleLarge,
            modifier = Modifier.padding(vertical = 20.dp).align(Alignment.CenterHorizontally)
        )
        Row(Modifier.fillMaxWidth()) {
            for (section in Section.values()) {
                Column(
                    Modifier
                        .weight(1f)
                        .clickable {
                            if (section != selectedSection) {
                                onSectionClick(section)
                            }
                        }, horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(section.sectionNameRes),
                        style = Typography.titleMedium,
                        modifier = Modifier.padding(vertical = 14.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(
                                if (section == selectedSection) BlueF4 else Color.Transparent
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(selectedSection = Section.CAMERA, onSectionClick = {})
}