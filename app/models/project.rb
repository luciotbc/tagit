class Project < ActiveRecord::Base
  belongs_to :user
  attr_accessible :goal, :name
  validate :user, :presence => true
  # Paperclip
  has_attached_file :video,
    :styles => {
      :thumb=> "100x100#",
      :small  => "150x150>" }
end
