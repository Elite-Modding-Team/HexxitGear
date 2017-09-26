/*
 * HexxitGear
 * Copyright (C) 2013  Ryan Cohen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package sct.hexxitgear.core.ability;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;

public class AbilityKnockback extends Ability {

	public AbilityKnockback() {
		super("ability.hexxitgear.knockback", 1 * 20, 10 * 20, true);
	}

	@Override
	public void start(EntityPlayer player) {
		List<EntityLivingBase> entities = player.world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(player.posX - 5, player.posY, player.posZ - 5, player.posX + 5, player.posY + 3, player.posZ + 5));

		for (EntityLivingBase entity : entities) {
			double relX = player.posX - entity.posX;
			double relZ = player.posZ - entity.posZ;

			if (!entity.equals((EntityLivingBase) player)) {
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
				entity.addVelocity((relX * 0.25) * -1, 0.2, (relZ * 0.25) * -1);
			}
		}
	}
}
