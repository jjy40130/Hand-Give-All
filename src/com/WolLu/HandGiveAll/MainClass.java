package com.WolLu.HandGiveAll;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player)sender;
		
		// 명령어 "/hga"가 입력되었을때
		if(cmd.getName().equalsIgnoreCase("hga")) {
			// 만약 OP가 없거나 버킷이라면
			if(!(sender instanceof Player)) {
				System.out.println("해당 명령어는 버킷에서 사용이 불가능합니다.");
				return false;
			} else if (!p.isOp()) {
				p.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " 명령어를 사용할 권한이 없습니다.");
				return false;
			}
			
			// 만약 인수값이 하나가 아니거나 0이라면
			if(args.length != 1) {
				p.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " 명령어 사용법 :" + ChatColor.YELLOW +" /hga 갯수");
				return false;
			} else if(Integer.parseInt(args[0]) < 1) {
				p.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " 최소 1개 이상의 아이템을 지급해야됩니다.");
				return false;
			}
			
			// 실행			
			@SuppressWarnings("deprecation")
			ItemStack item = p.getItemInHand().clone();
			
			item.setAmount(Integer.parseInt(args[0]));
			
			if(item.getAmount() < 1) {
				p.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " 최소 하나의 아이템을 손에 들고 있어야 합니다.");
			} else {
				for(Player all : Bukkit.getOnlinePlayers()) {
					all.getInventory().addItem(item);
					all.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " 아이템이 지급되었습니다 !");
				}
			}
		}
		return true;
	}
}
