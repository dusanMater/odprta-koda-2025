package com.example.consentform.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.consentform.ui.components.SignatureCanvas

@Composable
fun ConsentScreen(
        hasAgreed: Boolean,
        onAgreementChange: (Boolean) -> Unit,
        onSignatureChange: (String) -> Unit,
        onPrevious: () -> Unit,
        onNext: () -> Unit,
        isNextEnabled: Boolean,
        modifier: Modifier = Modifier
) {
    Column(
            modifier = modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
                text = "Consent Agreement",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
                modifier = Modifier.fillMaxWidth(),
                colors =
                        CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                        text = "Tattoo Consent Form",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                        text =
                                """
                        I understand that tattooing involves penetrating the skin and that there are risks associated with this procedure including, but not limited to:
                        
                        • Infection
                        • Allergic reactions to tattoo pigments
                        • Scarring
                        • Blood-borne pathogen transmission
                        
                        I confirm that:
                        • I am at least 18 years of age
                        • I am not under the influence of alcohol or drugs
                        • I have disclosed all medical conditions and medications
                        • I understand the aftercare instructions
                        • I understand that touch-ups may be necessary
                        
                        I release the tattoo artist and studio from any liability related to this procedure.
                    """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = hasAgreed, onCheckedChange = onAgreementChange)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                    text = "I have read and agree to the above terms",
                    style = MaterialTheme.typography.bodyMedium
            )
        }

        Text(
                text = "Signature",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
        )

        SignatureCanvas(onSignatureChanged = onSignatureChange, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.weight(1f))

        Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(onClick = onPrevious, modifier = Modifier.weight(1f)) {
                Text("Previous")
            }

            Button(onClick = onNext, enabled = isNextEnabled, modifier = Modifier.weight(1f)) {
                Text("Next")
            }
        }
    }
}
