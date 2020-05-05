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
		
		// ��ɾ� "/hga"�� �ԷµǾ�����
		if(cmd.getName().equalsIgnoreCase("hga")) {
			// ���� OP�� ���ų� ��Ŷ�̶��
			if(!(sender instanceof Player)) {
				System.out.println("�ش� ��ɾ�� ��Ŷ���� ����� �Ұ����մϴ�.");
				return false;
			} else if (!p.isOp()) {
				p.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " ��ɾ ����� ������ �����ϴ�.");
				return false;
			}
			
			// ���� �μ����� �ϳ��� �ƴϰų� 0�̶��
			if(args.length != 1) {
				p.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " ��ɾ� ���� :" + ChatColor.YELLOW +" /hga ����");
				return false;
			} else if(Integer.parseInt(args[0]) < 1) {
				p.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " �ּ� 1�� �̻��� �������� �����ؾߵ˴ϴ�.");
				return false;
			}
			
			// ����			
			@SuppressWarnings("deprecation")
			ItemStack item = p.getItemInHand().clone();
			
			item.setAmount(Integer.parseInt(args[0]));
			
			if(item.getAmount() < 1) {
				p.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " �ּ� �ϳ��� �������� �տ� ��� �־�� �մϴ�.");
			} else {
				for(Player all : Bukkit.getOnlinePlayers()) {
					all.getInventory().addItem(item);
					all.sendMessage(ChatColor.RED + "[" + ChatColor.WHITE + " HGA " + ChatColor.RED + "]" + ChatColor.WHITE + " �������� ���޵Ǿ����ϴ� !");
				}
			}
		}
		return true;
	}
}
