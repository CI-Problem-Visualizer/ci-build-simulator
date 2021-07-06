package ci.build.simulator.jenkins

import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.nio.charset.Charset

class Git(private val project: Project) {
    fun fetchAll() = checkCall("git", "fetch", "--all")

    fun up(branch: String) = checkCall("git", "push", "-u", "origin", branch)

    fun isBranchAlreadyExistingLocally(branch: String) =
        checkExitCode("git", "show-ref", "--verify", "--quiet", "refs/heads/$branch") == 0

    fun isBranchAlreadyExistingRemotely(branch: String) =
        checkExitCode("git", "show-ref", "--verify", "--quiet", "refs/remotes/origin/$branch") == 0

    fun deleteBranchLocally(branch: String) = checkCall("git", "branch", "-D", branch)

    fun deleteBranchRemotely(branch: String) = checkCall("git", "push", "--delete", "origin", branch)

    fun checkoutExistingBranch(branch: String) = checkCall("git", "checkout", branch)

    fun checkoutNewBranch(branch: String) = checkCall("git", "checkout", "-b", branch)

    fun verifyLocalWorkingTreeIsClean() {
        val isLocalWorkingTreeDirty = checkExitCode("git", "diff", "--quiet") != 0
        if (isLocalWorkingTreeDirty) {
            throw RuntimeException(
                "You have uncommitted local changes. Refusing to continue. " +
                        "Stash or commit your local changes, and then you can try running this task again."
            )
        }
    }

    fun currentBranch() = checkOutput("git", "branch", "--show-current")

    private fun checkCall(vararg args: String) {
        project.exec {
            commandLine(args.toMutableList())
        }.assertNormalExitValue()
    }

    private fun checkExitCode(vararg args: String): Int {
        return project.exec {
            isIgnoreExitValue = true
            commandLine(args.toMutableList())
        }.exitValue
    }

    private fun checkOutput(vararg args: String): String {
        val out = ByteArrayOutputStream()
        project.exec {
            standardOutput = out
            commandLine(args.toMutableList())
        }.assertNormalExitValue()
        return out.toString(Charset.defaultCharset().name()).trim()
    }
}