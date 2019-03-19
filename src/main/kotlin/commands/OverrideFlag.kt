package com.github.isturdy.aiflagtool.commands

import com.fs.starfarer.api.combat.ShipwideAIFlags
import com.github.isturdy.aiflagtool.AiFlagToolCombatPlugin
import org.lazywizard.console.BaseCommand
import org.lazywizard.console.Console

class OverrideFlag : BaseCommand {
    override fun runCommand(
        args: String,
        context: BaseCommand.CommandContext
    ): BaseCommand.CommandResult {
        val tokenized = args.split(" ")
        if (tokenized.size != 2 && tokenized.size != 3) return BaseCommand.CommandResult.BAD_SYNTAX
        val flag = try {
            ShipwideAIFlags.AIFlags.valueOf(tokenized[0])
        } catch (e: IllegalArgumentException) {
            Console.showMessage("No such flag '${tokenized[1]}'")
            return BaseCommand.CommandResult.ERROR
        }
        val persist = tokenized.size == 3;

        val plugin = AiFlagToolCombatPlugin.INSTANCE
        if (plugin == null) {
            Console.showMessage("AI Flag Tool plugin not found")
            return BaseCommand.CommandResult.ERROR
        }

        when (tokenized[1]) {
            "on" -> plugin.overrideFlag(flag, true, persist)
            "off" -> plugin.overrideFlag(flag, false, persist)
            "reset" -> plugin.resetFlag(flag)
            else -> return BaseCommand.CommandResult.BAD_SYNTAX
        }

        return BaseCommand.CommandResult.SUCCESS
    }
}