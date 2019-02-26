package com.github.isturdy.aiflagtool.fakes

import com.fs.starfarer.api.characters.PersonAPI
import com.fs.starfarer.api.combat.*
import com.fs.starfarer.api.graphics.SpriteAPI
import com.fs.starfarer.api.loading.WeaponSlotAPI
import org.lwjgl.util.vector.Vector2f
import java.awt.Color
import java.util.*

class FakeShipAPI(private val name: String) : ShipAPI {
    private val flags = ShipwideAIFlags()
    private var shipTarget: ShipAPI? = null

    override fun abortLanding() {
        TODO("not implemented")
    }

    override fun addAfterimage(
        color: Color?,
        locX: Float,
        locY: Float,
        velX: Float,
        velY: Float,
        maxJitter: Float,
        `in`: Float,
        dur: Float,
        out: Float,
        additive: Boolean,
        combineWithSpriteColor: Boolean,
        aboveShip: Boolean
    ) {
        TODO("not implemented")
    }

    override fun applyCriticalMalfunction(module: Any?) {
        TODO("not implemented")
    }

    override fun applyCriticalMalfunction(module: Any?, permanent: Boolean) {
        TODO("not implemented")
    }

    override fun areAnyEnemiesInRange(): Boolean {
        TODO("not implemented")
    }

    override fun areSignificantEnemiesInRange(): Boolean {
        TODO("not implemented")
    }

    override fun beginLandingAnimation(target: ShipAPI?) {
        TODO("not implemented")
    }

    override fun blockCommandForOneFrame(command: ShipCommand?) {
        TODO("not implemented")
    }

    override fun clearDamageDecals() {
        TODO("not implemented")
    }

    override fun controlsLocked(): Boolean {
        TODO("not implemented")
    }

    override fun ensureClonedStationSlotSpec() {
        TODO("not implemented")
    }

    override fun fadeToColor(source: Any?, color: Color?, durIn: Float, durOut: Float, maxShift: Float) {
        TODO("not implemented")
    }

    override fun getAI(): Any {
        TODO("not implemented")
    }

    override fun getAIFlags(): ShipwideAIFlags {
        return flags
    }

    override fun getAcceleration(): Float {
        TODO("not implemented")
    }

    override fun getAllWeapons(): MutableList<WeaponAPI> {
        TODO("not implemented")
    }

    override fun getAllWings(): MutableList<FighterWingAPI> {
        TODO("not implemented")
    }

    override fun getAngularVelocity(): Float {
        TODO("not implemented")
    }

    override fun getArmorGrid(): ArmorGridAPI {
        TODO("not implemented")
    }

    override fun getBaseCriticalMalfunctionDamage(): Float {
        TODO("not implemented")
    }

    override fun getCRAtDeployment(): Float {
        TODO("not implemented")
    }

    override fun getCaptain(): PersonAPI {
        TODO("not implemented")
    }

    override fun getChildModulesCopy(): MutableList<ShipAPI> {
        TODO("not implemented")
    }

    override fun getCollisionClass(): CollisionClass {
        TODO("not implemented")
    }

    override fun getCollisionRadius(): Float {
        TODO("not implemented")
    }

    override fun getCombinedAlphaMult(): Float {
        TODO("not implemented")
    }

    override fun getCopyLocation(): Vector2f {
        TODO("not implemented")
    }

    override fun getCurrFlux(): Float {
        TODO("not implemented")
    }

    override fun getCurrentCR(): Float {
        TODO("not implemented")
    }

    override fun getDeceleration(): Float {
        TODO("not implemented")
    }

    override fun getDeployCost(): Float {
        TODO("not implemented")
    }

    override fun getDeployedDrones(): MutableList<ShipAPI> {
        TODO("not implemented")
    }

    override fun getDisabledWeapons(): MutableSet<WeaponAPI> {
        TODO("not implemented")
    }

    override fun getDroneSource(): ShipAPI {
        TODO("not implemented")
    }

    override fun getEngineController(): ShipEngineControllerAPI {
        TODO("not implemented")
    }

    override fun getEngineFractionPermanentlyDisabled(): Float {
        TODO("not implemented")
    }

    override fun getExactBounds(): BoundsAPI {
        TODO("not implemented")
    }

    override fun getFacing(): Float {
        TODO("not implemented")
    }

    override fun getFighterTimeBeforeRefit(): Float {
        TODO("not implemented")
    }

    override fun getFixedLocation(): Vector2f {
        TODO("not implemented")
    }

    override fun getFleetMemberId(): String {
        TODO("not implemented")
    }

    override fun getFluxLevel(): Float {
        TODO("not implemented")
    }

    override fun getFluxTracker(): FluxTrackerAPI {
        TODO("not implemented")
    }

    override fun getFullTimeDeployed(): Float {
        TODO("not implemented")
    }

    override fun getHardFluxLevel(): Float {
        TODO("not implemented")
    }

    override fun getHitpoints(): Float {
        TODO("not implemented")
    }

    override fun getOriginalCaptain(): PersonAPI {
        TODO("not implemented")
    }

    override fun getHullLevel(): Float {
        TODO("not implemented")
    }

    override fun getHullLevelAtDeployment(): Float {
        TODO("not implemented")
    }

    override fun getHullSize(): ShipAPI.HullSize {
        TODO("not implemented")
    }

    override fun getHullSpec(): ShipHullSpecAPI {
        TODO("not implemented")
    }

    override fun getHullStyleId(): String {
        TODO("not implemented")
    }

    override fun getId(): String {
        TODO("not implemented")
    }

    override fun getLargeHardpointCover(): SpriteAPI {
        TODO("not implemented")
    }

    override fun getLargeTurretCover(): SpriteAPI {
        TODO("not implemented")
    }

    override fun getLaunchBaysCopy(): MutableList<FighterLaunchBayAPI> {
        TODO("not implemented")
    }

    override fun getLocation(): Vector2f {
        TODO("not implemented")
    }

    override fun getLowestHullLevelReached(): Float {
        TODO("not implemented")
    }

    override fun getMass(): Float {
        TODO("not implemented")
    }

    override fun getMassWithModules(): Float {
        TODO("not implemented")
    }

    override fun getMaxFlux(): Float {
        TODO("not implemented")
    }

    override fun getMaxHitpoints(): Float {
        TODO("not implemented")
    }

    override fun getMaxSpeed(): Float {
        TODO("not implemented")
    }

    override fun getMaxSpeedWithoutBoost(): Float {
        TODO("not implemented")
    }

    override fun getMaxTurnRate(): Float {
        TODO("not implemented")
    }

    override fun getMediumHardpointCover(): SpriteAPI {
        TODO("not implemented")
    }

    override fun getMediumTurretCover(): SpriteAPI {
        TODO("not implemented")
    }

    override fun getMinFlux(): Float {
        TODO("not implemented")
    }

    override fun getMinFluxLevel(): Float {
        TODO("not implemented")
    }

    override fun getModuleOffset(): Vector2f {
        TODO("not implemented")
    }

    override fun getMouseTarget(): Vector2f {
        TODO("not implemented")
    }

    override fun getMutableStats(): MutableShipStatsAPI {
        TODO("not implemented")
    }

    override fun getName(): String {
        return name
    }

    override fun getNumFighterBays(): Int {
        TODO("not implemented")
    }

    override fun getNumFlameouts(): Int {
        TODO("not implemented")
    }

    override fun getOriginalOwner(): Int {
        TODO("not implemented")
    }

    override fun getOverloadColor(): Color {
        TODO("not implemented")
    }

    override fun getOwner(): Int {
        TODO("not implemented")
    }

    override fun getParentStation(): ShipAPI {
        TODO("not implemented")
    }

    override fun getPhaseCloak(): ShipSystemAPI {
        TODO("not implemented")
    }

    override fun getRenderOffset(): Vector2f {
        TODO("not implemented")
    }

    override fun getSelectedGroupAPI(): WeaponGroupAPI {
        TODO("not implemented")
    }

    override fun getSharedFighterReplacementRate(): Float {
        TODO("not implemented")
    }

    override fun getShield(): ShieldAPI {
        TODO("not implemented")
    }

    override fun getShipAI(): ShipAIPlugin {
        TODO("not implemented")
    }

    override fun getShipTarget(): ShipAPI? {
        return shipTarget
    }

    override fun getSmallHardpointCover(): SpriteAPI {
        TODO("not implemented")
    }

    override fun getSmallTurretCover(): SpriteAPI {
        TODO("not implemented")
    }

    override fun getSpriteAPI(): SpriteAPI {
        TODO("not implemented")
    }

    override fun getStationSlot(): WeaponSlotAPI {
        TODO("not implemented")
    }

    override fun getSystem(): ShipSystemAPI {
        TODO("not implemented")
    }

    override fun getTimeDeployedForCRReduction(): Float {
        TODO("not implemented")
    }

    override fun getTimeDeployedUnderPlayerControl(): Float {
        TODO("not implemented")
    }

    override fun getTravelDrive(): ShipSystemAPI {
        TODO("not implemented")
    }

    override fun getTurnAcceleration(): Float {
        TODO("not implemented")
    }

    override fun getTurnDeceleration(): Float {
        TODO("not implemented")
    }

    override fun getUsableWeapons(): MutableList<WeaponAPI> {
        TODO("not implemented")
    }

    override fun getVariant(): ShipVariantAPI {
        TODO("not implemented")
    }

    override fun getVelocity(): Vector2f {
        TODO("not implemented")
    }

    override fun getVentCoreColor(): Color {
        TODO("not implemented")
    }

    override fun getVentCoreTexture(): String {
        TODO("not implemented")
    }

    override fun getVentFringeColor(): Color {
        TODO("not implemented")
    }

    override fun getVentFringeTexture(): String {
        TODO("not implemented")
    }

    override fun getVisualBounds(): BoundsAPI {
        TODO("not implemented")
    }

    override fun getWeaponGroupFor(weapon: WeaponAPI?): WeaponGroupAPI {
        TODO("not implemented")
    }

    override fun getWeaponGroupsCopy(): MutableList<WeaponGroupAPI> {
        TODO("not implemented")
    }

    override fun getWing(): FighterWingAPI {
        TODO("not implemented")
    }

    override fun getWingCRAtDeployment(): Float {
        TODO("not implemented")
    }

    override fun getWingLeader(): ShipAPI {
        TODO("not implemented")
    }

    override fun getWingToken(): Any {
        TODO("not implemented")
    }

    override fun giveCommand(command: ShipCommand?, param: Any?, groupNumber: Int) {
        TODO("not implemented")
    }

    override fun hasLaunchBays(): Boolean {
        TODO("not implemented")
    }

    override fun hasRadarRibbonIcon(): Boolean {
        TODO("not implemented")
    }

    override fun isAffectedByNebula(): Boolean {
        TODO("not implemented")
    }

    override fun isAlive(): Boolean {
        TODO("not implemented")
    }

    override fun isAlly(): Boolean {
        TODO("not implemented")
    }

    override fun isCapital(): Boolean {
        TODO("not implemented")
    }

    override fun isCruiser(): Boolean {
        TODO("not implemented")
    }

    override fun isDefenseDisabled(): Boolean {
        TODO("not implemented")
    }

    override fun isDestroyer(): Boolean {
        TODO("not implemented")
    }

    override fun isDirectRetreat(): Boolean {
        TODO("not implemented")
    }

    override fun isDrone(): Boolean {
        TODO("not implemented")
    }

    override fun isFighter(): Boolean {
        TODO("not implemented")
    }

    override fun isFinishedLanding(): Boolean {
        TODO("not implemented")
    }

    override fun isFrigate(): Boolean {
        TODO("not implemented")
    }

    override fun isHoldFire(): Boolean {
        TODO("not implemented")
    }

    override fun isHoldFireOneFrame(): Boolean {
        TODO("not implemented")
    }

    override fun isHulk(): Boolean {
        TODO("not implemented")
    }

    override fun isInsideNebula(): Boolean {
        TODO("not implemented")
    }

    override fun isInvalidTransferCommandTarget(): Boolean {
        TODO("not implemented")
    }

    override fun isJitterShields(): Boolean {
        TODO("not implemented")
    }

    override fun isLanding(): Boolean {
        TODO("not implemented")
    }

    override fun isPhased(): Boolean {
        TODO("not implemented")
    }

    override fun isPiece(): Boolean {
        TODO("not implemented")
    }

    override fun isPullBackFighters(): Boolean {
        TODO("not implemented")
    }

    override fun isRecentlyShotByPlayer(): Boolean {
        TODO("not implemented")
    }

    override fun isRenderEngines(): Boolean {
        TODO("not implemented")
    }

    override fun isRetreating(): Boolean {
        TODO("not implemented")
    }

    override fun isSelectableInWarroom(): Boolean {
        TODO("not implemented")
    }

    override fun isShipWithModules(): Boolean {
        TODO("not implemented")
    }

    override fun isShowModuleJitterUnder(): Boolean {
        TODO("not implemented")
    }

    override fun isShuttlePod(): Boolean {
        TODO("not implemented")
    }

    override fun isStation(): Boolean {
        TODO("not implemented")
    }

    override fun isStationModule(): Boolean {
        TODO("not implemented")
    }

    override fun isTargetable(): Boolean {
        TODO("not implemented")
    }

    override fun isWingLeader(): Boolean {
        TODO("not implemented")
    }

    override fun losesCRDuringCombat(): Boolean {
        TODO("not implemented")
    }

    override fun removeWeaponFromGroups(weapon: WeaponAPI?) {
        TODO("not implemented")
    }

    override fun resetDefaultAI() {
        TODO("not implemented")
    }

    override fun resetOriginalOwner() {
        TODO("not implemented")
    }

    override fun resetOverloadColor() {
        TODO("not implemented")
    }

    override fun setAffectedByNebula(affectedByNebula: Boolean) {
        TODO("not implemented")
    }

    override fun setAlly(ally: Boolean) {
        TODO("not implemented")
    }

    override fun setAngularVelocity(angVel: Float) {
        TODO("not implemented")
    }

    override fun setApplyExtraAlphaToEngines(applyExtraAlphaToEngines: Boolean) {
        TODO("not implemented")
    }

    override fun setCRAtDeployment(cr: Float) {
        TODO("not implemented")
    }

    override fun setCollisionClass(collisionClass: CollisionClass?) {
        TODO("not implemented")
    }

    override fun setCollisionRadius(radius: Float) {
        TODO("not implemented")
    }

    override fun setControlsLocked(controlsLocked: Boolean) {
        TODO("not implemented")
    }

    override fun setCopyLocation(loc: Vector2f?, copyAlpha: Float, copyFacing: Float) {
        TODO("not implemented")
    }

    override fun setCurrentCR(cr: Float) {
        TODO("not implemented")
    }

    override fun setDHullOverlay(spriteName: String?) {
        TODO("not implemented")
    }

    override fun setDefenseDisabled(defenseDisabled: Boolean) {
        TODO("not implemented")
    }

    override fun setExtraAlphaMult(transparency: Float) {
        TODO("not implemented")
    }

    override fun setFacing(facing: Float) {
        TODO("not implemented")
    }

    override fun setFighterTimeBeforeRefit(fighterTimeBeforeRefit: Float) {
        TODO("not implemented")
    }

    override fun setFixedLocation(fixedLocation: Vector2f?) {
        TODO("not implemented")
    }

    override fun setHeavyDHullOverlay() {
        TODO("not implemented")
    }

    override fun setHitpoints(value: Float) {
        TODO("not implemented")
    }

    override fun setHoldFireOneFrame(holdFireOneFrame: Boolean) {
        TODO("not implemented")
    }

    override fun setHullSize(hullSize: ShipAPI.HullSize?) {
        TODO("not implemented")
    }

    override fun setInsideNebula(isInsideNebula: Boolean) {
        TODO("not implemented")
    }

    override fun setInvalidTransferCommandTarget(invalidTransferCommandTarget: Boolean) {
        TODO("not implemented")
    }

    override fun setJitter(source: Any?, color: Color?, intensity: Float, copies: Int, range: Float) {
        TODO("not implemented")
    }

    override fun setJitter(source: Any?, color: Color?, intensity: Float, copies: Int, minRange: Float, range: Float) {
        TODO("not implemented")
    }

    override fun setJitterShields(jitterShields: Boolean) {
        TODO("not implemented")
    }

    override fun setJitterUnder(source: Any?, color: Color?, intensity: Float, copies: Int, range: Float) {
        TODO("not implemented")
    }

    override fun setJitterUnder(
        source: Any?,
        color: Color?,
        intensity: Float,
        copies: Int,
        minRange: Float,
        range: Float
    ) {
        TODO("not implemented")
    }

    override fun setLightDHullOverlay() {
        TODO("not implemented")
    }

    override fun setMass(mass: Float) {
        TODO("not implemented")
    }

    override fun setMaxHitpoints(maxArmor: Float) {
        TODO("not implemented")
    }

    override fun setMediumDHullOverlay() {
        TODO("not implemented")
    }

    override fun setOriginalOwner(originalOwner: Int) {
        TODO("not implemented")
    }

    override fun setOverloadColor(color: Color?) {
        TODO("not implemented")
    }

    override fun setOwner(owner: Int) {
        TODO("not implemented")
    }

    override fun setParentStation(station: ShipAPI?) {
        TODO("not implemented")
    }

    override fun setPhased(phased: Boolean) {
        TODO("not implemented")
    }

    override fun setPullBackFighters(pullBackFighters: Boolean) {
        TODO("not implemented")
    }

    override fun setRenderBounds(renderBounds: Boolean) {
        TODO("not implemented")
    }

    override fun setRenderEngines(renderEngines: Boolean) {
        TODO("not implemented")
    }

    override fun setRetreating(retreating: Boolean, direct: Boolean) {
        TODO("not implemented")
    }

    override fun setShield(type: ShieldAPI.ShieldType?, shieldUpkeep: Float, shieldEfficiency: Float, arc: Float) {
        TODO("not implemented")
    }

    override fun setShipAI(ai: ShipAIPlugin?) {
        TODO("not implemented")
    }

    override fun setShipSystemDisabled(systemDisabled: Boolean) {
        TODO("not implemented")
    }

    override fun setShipTarget(ship: ShipAPI?) {
        shipTarget = ship
    }

    override fun setShipWithModules(isShipWithModules: Boolean) {
        TODO("not implemented")
    }

    override fun setShowModuleJitterUnder(showModuleJitterUnder: Boolean) {
        TODO("not implemented")
    }

    override fun setSprite(category: String?, key: String?) {
        TODO("not implemented")
    }

    override fun setStation(isStation: Boolean) {
        TODO("not implemented")
    }

    override fun setStationSlot(stationSlot: WeaponSlotAPI?) {
        TODO("not implemented")
    }

    override fun setVentCoreColor(color: Color?) {
        TODO("not implemented")
    }

    override fun setVentCoreTexture(textureId: String?) {
        TODO("not implemented")
    }

    override fun setVentFringeColor(color: Color?) {
        TODO("not implemented")
    }

    override fun setVentFringeTexture(textureId: String?) {
        TODO("not implemented")
    }

    override fun setWeaponGlow(glow: Float, color: Color?, types: EnumSet<WeaponAPI.WeaponType>?) {
        TODO("not implemented")
    }

    override fun splitShip(): ShipAPI {
        TODO("not implemented")
    }

    override fun syncWeaponDecalsWithArmorDamage() {
        TODO("not implemented")
    }

    override fun syncWithArmorGridState() {
        TODO("not implemented")
    }

    override fun toggleTravelDrive() {
        TODO("not implemented")
    }

    override fun toString(): String {
        return name
    }

    override fun turnOffTravelDrive() {
        TODO("not implemented")
    }

    override fun turnOnTravelDrive() {
        TODO("not implemented")
    }

    override fun turnOnTravelDrive(dur: Float) {
        TODO("not implemented")
    }

    override fun useSystem() {
        TODO("not implemented")
    }

    override fun getWingMembers(): MutableList<ShipAPI> {
        TODO("not implemented")
    }
}