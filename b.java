package doc.base_tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import steps.RunnerExtension;

import java.util.UUID;

import static steps.RedactedProcess.deleteProcessInstance;

@ExtendWith(RunnerExtension.class)
public class BaseTest {
    public String processName;
    public UUID documentId;
    public static boolean assertionFailureHappened = false;

    @AfterEach
    @Step("redacted string")
    void deleteInstance() {
        if (!assertionFailureHappened) {
            deleteProcessInstance(documentId, processName);
        }
    }
}